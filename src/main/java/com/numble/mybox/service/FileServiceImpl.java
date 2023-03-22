package com.numble.mybox.service;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.numble.mybox.dto.FileDto;
import com.numble.mybox.entity.FileMeta;
import com.numble.mybox.entity.User;
import com.numble.mybox.entity.UserFileUsage;
import com.numble.mybox.repository.FileMetaRepository;
import com.numble.mybox.repository.UserRepository;
import com.numble.mybox.request.FileUploadRequest;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service @Transactional
public class FileServiceImpl implements FileService{

	private final FileProcessor fileProcessor;
	private final UserRepository userRepository;
	private final FileMetaRepository fileMetaRepository;

	@Override
	public void uploadFiles(Long userId, FileUploadRequest request) {

		for (FileUploadRequest.File file : request.getFiles()) {
			try {

				FileDto fileDto = fileProcessor.uploadFile(file.getFile(), file.getFolderPath(), userId);

				User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("유저없음"));
				UserFileUsage userFileUsage = user.getUserFileUsage();
				userFileUsage.updateUsedUsage(fileDto.getFileSize());

				FileMeta fileEntity = FileMeta.builder()
					.user(user)
					.fileOriginName(fileDto.getFileOriginName())
					.fileSaveName(fileDto.getFileSaveName())
					.fileExt(fileDto.getFileExt())
					.filePath(fileDto.getFilePath())
					.build();

				fileMetaRepository.save(fileEntity);

			} catch (IOException e) {
				log.error("file upload error {0}", e);
			}
		}
	}

	@Override
	public void downloadFile(Long fileId, Long userId, HttpServletResponse response) throws IOException {

		FileMeta fileMeta = fileMetaRepository.findById(fileId).orElseThrow(() -> new IllegalArgumentException("File 없음"));
		User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User 없음"));
		user.checkEqualUserOrThrow(fileMeta.getUser().getId());

		fileProcessor.fileDownload(response, fileMeta);
	}

	@Override
	public void removeFile(Long userId, Long fileId) {

	}
}
