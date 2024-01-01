package com.hiro0118.tennisapi.repositories.time;

import com.hiro0118.tennisapi.domain.time.entities.TimeEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

@Mapper
public interface ITimeMapper {

    @SelectProvider(ITimeMapper.TimeProvider.class)
    List<TimeEntity> getTimes();

    class TimeProvider implements ProviderMethodResolver {
        public String getTimes(String todoId) {
            return new SQL() {{
                SELECT("time");
                FROM("time");
            }}.toString();
        }
    }
}
