package com.numble.mybox.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.numble.mybox.dto.FileDto;
import com.numble.mybox.entity.File;
import com.numble.mybox.entity.User;
import com.numble.mybox.entity.UserFileUsage;
import com.numble.mybox.repository.FileRepository;
import com.numble.mybox.repository.UserRepository;
import com.numble.mybox.request.FileRequest;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service @Transactional
public class FileServiceImpl implements FileService{

	private final FileUploader fileUploader;
	private final UserRepository userRepository;
	private final FileRepository fileRepository;

	@Override
	public void uploadFiles(Long userId, FileRequest request) {

		for (FileRequest.File file : request.getFiles()) {
			try {

				FileDto fileDto = fileUploader.uploadFile(file.getFile(), file.getFolderPath(), userId);

				User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("유저없음"));
				UserFileUsage userFileUsage = user.getUserFileUsage();
				userFileUsage.updateUsedUsage(fileDto.getFileSize());

				File fileEntity = File.builder()
					.user(user)
					.fileOriginName(fileDto.getFileOriginName())
					.fileSaveName(fileDto.getFileSaveName())
					.fileExt(fileDto.getFileExt())
					.filePath(fileDto.getFilePath())
					.build();

				fileRepository.save(fileEntity);

			} catch (IOException e) {
				log.error("file upload error {0}", e);
			}
		}
	}

	@Override
	public void downloadFile(Long userId, Long fileId, HttpServletResponse response) {

	}

	@Override
	public void removeFile(Long userId, Long fileId) {

	}
}
