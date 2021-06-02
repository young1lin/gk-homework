package me.young1lin.spring.cloud.server.config;

import java.util.Objects;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author <a href="mailto:young1lin0108@gmail.com">young1lin</a>
 * @since 2021/6/1 下午11:43
 * @version 1.0
 */
@ConfigurationProperties("spring.cloud.config.server.file")
public class FileEnvironmentRepositoryProperties{

	private int order;

	private String filePath;

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		FileEnvironmentRepositoryProperties that = (FileEnvironmentRepositoryProperties) o;
		return order == that.order &&
				Objects.equals(filePath, that.filePath);
	}

	@Override
	public int hashCode() {
		return Objects.hash(order, filePath);
	}

	@Override
	public String toString() {
		return "FileEnvironmentRepositoryProperties{" +
				"order=" + order +
				", filePath='" + filePath + '\'' +
				'}';
	}

}
