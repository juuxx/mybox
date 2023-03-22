package com.numble.mybox.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import com.numble.mybox.dto.FileDto;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MyFileUploader implements FileUploader {

	@Value("${spring.servlet.multipart.location}")
	private String uploadPath;

	@Override
	public FileDto uploadFile(MultipartFile multipartFile, String folderPath, Long userId) throws IOException {
		String extension = this.getExtensionByStringHandling(multipartFile.getOriginalFilename()).orElseThrow(() -> new IllegalArgumentException("잘못된 파일입니다."));
		File uploadFile = this.convert(multipartFile, extension).orElseThrow(() -> new IllegalArgumentException("MultipartFile -> File 변환 실패"));
		return this.upload(multipartFile, uploadFile, folderPath, extension, multipartFile.getOriginalFilename());
	}

	private Optional<String> getExtensionByStringHandling(String filename) {
		return Optional.ofNullable(filename)
			.filter(f -> f.contains("."))
			.map(f -> f.substring(filename.lastIndexOf(".") + 1));
	}

	private Optional<File> convert(MultipartFile file, String extension) throws IOException {
		File convertFile = new File(generateUniqueFileNameWithExtension(extension));
		if (convertFile.createNewFile()) {
			try (FileOutputStream fos = new FileOutputStream(convertFile)) {
				fos.write(file.getBytes());
			} catch (Exception e) {
				convertFile.delete();
			}
			return Optional.of(convertFile);
		}
		return Optional.empty();
	}

	private String generateUniqueFileNameWithExtension(String extension) {
		String uniqueFileName = UUID.randomUUID().toString();
		return uniqueFileName + "." + extension;
	}

	private FileDto upload(MultipartFile multipartFile, File uploadFile, String folderPath, String extension, String originName) {
		String fileName = this.uploadPath + File.separator + folderPath + File.separator + uploadFile.getName();
		String filePath = this.upload(multipartFile, fileName);
		FileDto dto = FileDto.builder()
			.fileSaveName(uploadFile.getName())
			.fileOriginName(originName)
			.fileSize(uploadFile.length())
			.fileExt(extension)
			.filePath(filePath)
			.build();

		this.removeNewFile(uploadFile);

		return dto;
	}

	private void removeNewFile(File targetFile) {
		if (targetFile.delete()) {
			log.info("파일이 삭제되었습니다.");
		} else {
			log.info("파일이 삭제되지 못했습니다.");
		}
	}

	private String upload(MultipartFile uploadFile, String fileName) {
		try {
			this.makeFolder(fileName);
			File destination = new File(fileName);
			Path path = Paths.get(fileName);
			uploadFile.transferTo(destination);

			String url = path.toString();

			if (!ObjectUtils.isEmpty(url)) {
				url = url.replace(this.uploadPath, "");
			}

			return url;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	private void makeFolder(String fileName) {
		File uploadPathFolder = new File(fileName);
		if (!uploadPathFolder.exists()) {
			uploadPathFolder.mkdirs();
		}
	}
}
