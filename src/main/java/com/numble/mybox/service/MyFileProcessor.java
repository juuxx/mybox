package com.numble.mybox.service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import com.numble.mybox.dto.FileDto;
import com.numble.mybox.entity.FileMeta;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MyFileProcessor implements FileProcessor {

	@Value("${spring.servlet.multipart.location}")
	private String uploadPath;

	@Override
	public FileDto uploadFile(MultipartFile multipartFile, String folderPath, Long userId) throws IOException {
		String extension = this.getExtensionByStringHandling(multipartFile.getOriginalFilename()).orElseThrow(() -> new IllegalArgumentException("잘못된 파일입니다."));
		File uploadFile = this.convert(multipartFile, extension).orElseThrow(() -> new IllegalArgumentException("MultipartFile -> File 변환 실패"));
		return this.upload(multipartFile, uploadFile, folderPath, extension, multipartFile.getOriginalFilename());
	}

	@Override
	public void fileDownload(HttpServletResponse response, FileMeta fileMeta) throws IOException {
			File file = new File(uploadPath + File.separator + fileMeta.getFilePath());

			// 파일이 존재하지 않으면 404 오류를 반환합니다.
			if (!file.exists()) {
				response.setStatus(HttpStatus.NOT_FOUND.value());
				throw new IllegalArgumentException("파일을 찾을 수 없습니다.");
			}

			// Content-Type을 지정합니다.
			response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);

			// Content-Disposition 헤더를 지정하여 파일 이름을 설정합니다.
			response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileMeta.getFileOriginName() + "\"");

			// 파일 데이터를 읽어들이기 위한 InputStream을 생성합니다.
			InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

			// 파일 데이터를 읽어들이고, response에 쓰기 위한 OutputStream을 생성합니다.
			OutputStream outputStream = response.getOutputStream();
			byte[] buffer = new byte[1024];
			int bytesRead;
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
			}

			// 스트림을 닫습니다.
			outputStream.flush();
			outputStream.close();
			inputStream.close();

	}

	@Override
	public void deleteFile(String filePath) {
		File file = new File(uploadPath + File.separator + filePath);

		try {

			boolean delete = file.delete();

			if (!delete) {
				throw new IllegalArgumentException("파일을 삭제하지 못했습니다.");
			}

		} catch (Exception e) {
			throw new IllegalArgumentException("파일 삭제 중 오류가 발생하였습니다: " + e.getMessage());
		}
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
