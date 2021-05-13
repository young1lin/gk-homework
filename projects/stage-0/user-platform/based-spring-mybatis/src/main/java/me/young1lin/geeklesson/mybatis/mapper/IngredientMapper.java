package me.young1lin.geeklesson.mybatis.mapper;

import java.util.List;

import me.young1lin.geeklesson.mybatis.domain.Ingredient;
import org.apache.ibatis.annotations.Mapper;

import org.springframework.stereotype.Repository;

/**
 * @author <a href="mailto:young1lin0108@gmail.com">young1lin</a>
 * @since 2021/5/12 下午9:16
 * @version 1.0
 */
@Repository
@Mapper
public interface IngredientMapper {

	/**
	 * 列出所有表中信息
	 * 这个其实是写 《Spring In Action》的 demo 代码的表。
	 *
	 * @return 所有 Ingredient。
	 */
	List<Ingredient> listAll();

}
