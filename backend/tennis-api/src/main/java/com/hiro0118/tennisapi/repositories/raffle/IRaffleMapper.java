package com.hiro0118.tennisapi.repositories.raffle;

import com.hiro0118.tennisapi.domain.raffle.entities.RaffleStatusEntity;
import com.hiro0118.tennisapi.repositories.time.ITimeMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface IRaffleMapper {

    @SelectProvider(IRaffleMapper.RaffleProvider.class)
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
                SELECT("date", "time", "park_id", "num_of_applications");
                FROM("raffle_status");
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

        private static boolean listIsEmpty(List<String> list) {
            return list == null || list.isEmpty();
        }

        private static String in(final String name, final List<String> conditions) {
            var ors = conditions
                .stream()
                .map(cond -> name + "='" + cond + "'")
                .collect(Collectors.joining(" OR "));
            return "(" + ors + ")";
        }
    }
}
