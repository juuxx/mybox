package com.numble.mybox.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
public class FileDownloadRequest {
	private String fileUrl;
	private String fileName;
}
