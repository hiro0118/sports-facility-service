package com.hiro0118.tennisapi.repositories.park;

import com.hiro0118.tennisapi.domain.park.entities.ParkEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

@Mapper
public interface IParkMapper {

    @SelectProvider(IParkMapper.ParkProvider.class)
    List<ParkEntity> getParks();

    class ParkProvider implements ProviderMethodResolver {
        public String getParks() {
            return new SQL() {{
                SELECT("id", "name", "postal_code", "address", "num_of_courts");
                FROM("park");
            }}.toString();
        }
    }
}
