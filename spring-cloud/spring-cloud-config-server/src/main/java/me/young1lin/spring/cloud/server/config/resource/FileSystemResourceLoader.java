package me.young1lin.spring.cloud.server.config.resource;

import java.io.File;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

/**
 * @author <a href="mailto:young1lin0108@gmail.com">young1lin</a>
 * @since 2021/6/1 下午11:08
 * @version 1.0
 */
public class FileSystemResourceLoader extends DefaultResourceLoader {

	@Override
	protected Resource getResourceByPath(String path) {
		File file = new File(path);
		return new FileSystemContextResource(file);
	}

}