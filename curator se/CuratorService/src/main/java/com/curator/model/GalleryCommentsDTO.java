package com.curator.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GalleryCommentsDTO {
    private int id;
	private int parentId;
	private String comment;
	private int commentedBy;
	private List<GalleryCommentsDTO> child;
}
