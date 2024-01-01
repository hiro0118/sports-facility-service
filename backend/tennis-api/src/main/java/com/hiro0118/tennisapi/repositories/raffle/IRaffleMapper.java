package com.hiro0118.tennisapi.repositories.raffle;

import com.hiro0118.tennisapi.domain.raffle.RaffleStatusEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

import static com.hiro0118.tennisapi.repositories.RepositoryUtils.in;
import static com.hiro0118.tennisapi.repositories.RepositoryUtils.listIsEmpty;

@Mapper
public interface IRaffleMapper {

    @SelectProvider(RaffleProvider.class)
    List<RaffleStatusEntity> getRaffleStatus(
        List<String> dateList,
        List<String> timeList,
        List<String> parkIdList
    );

    class RaffleProvider implements ProviderMethodResolver {
        public String getRaffleStatus(
            final List<String> dateList,
            final List<String> timeList,
            final List<String> parkIdList
        ) {
            return new SQL() {{
                SELECT("rs.date");
                SELECT("rs.time");
                SELECT("rs.park_id");
                SELECT("p.name AS park_name");
                SELECT("p.num_of_courts");
                SELECT("rs.num_of_applications");
                FROM("raffle_status rs");
                INNER_JOIN("park p ON rs.park_id = p.id");
                if (!listIsEmpty(dateList)) {
                    WHERE(in("date", dateList));
                }
                if (!listIsEmpty(timeList)) {
                    WHERE(in("time", timeList));
                }
                if (!listIsEmpty(parkIdList)) {
                    WHERE(in("park_id", parkIdList));
                }
                ORDER_BY("date", "time", "park_id");
            }}.toString();
        }
    }
}
