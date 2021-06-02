package me.young1lin.spring.cloud.server.config.resource;

import java.io.File;

import org.springframework.core.io.ContextResource;
import org.springframework.core.io.FileSystemResource;

/**
 * @author <a href="mailto:young1lin0108@gmail.com">young1lin</a>
 * @since 2021/6/1 下午11:08
 * @version 1.0
 */
public class FileSystemContextResource extends FileSystemResource implements ContextResource {


	public FileSystemContextResource(File file) {
		super(file);
	}

	@Override
	public String getPathWithinContext() {
		return getPath();
	}

}