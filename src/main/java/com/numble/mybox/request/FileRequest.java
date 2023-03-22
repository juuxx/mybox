package com.numble.mybox.request;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileRequest {
	private List<File> files;

	@Getter
	@Setter
	public static class File {
		private MultipartFile file;
		private String folderPath;
	}
}
