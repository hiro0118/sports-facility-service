package com.hiro0118.tennisapi.repositories.day;

import com.hiro0118.tennisapi.domain.day.DayEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

@Mapper
public interface IDayMapper {

    @SelectProvider(DayProvider.class)
    List<DayEntity> getDays();

     class DayProvider implements ProviderMethodResolver {

        public String getDays() {
            return new SQL() {{
                SELECT("id, name, short_name");
                FROM("day");
            }}.toString();
        }
    }
}
