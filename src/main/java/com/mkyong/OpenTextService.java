package com.mkyong;

import java.io.File;

public interface OpenTextService {
	
	
	String getToken() throws Exception;

	String applyMetadata(String nodeId, MetadataCategoriesDto categories);

	boolean sendReportsToOpenText();

	long uploadFile(File file, String fileName) throws Exception;

}
