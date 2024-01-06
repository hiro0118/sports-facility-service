package com.hiro0118.tennisapi.repositories.notificationconfig;

import com.hiro0118.tennisapi.domain.date.DateEntity;
import com.hiro0118.tennisapi.domain.notificationconfig.data.NotificationConfigBaseData;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

@Mapper
public interface INotificationConfigMapper {

    // Base Config
    @SelectProvider(NotificationConfigProvider.class)
    NotificationConfigBaseData getNotificationConfigBaseDataById(String id);

//    @InsertProvider(NotificationConfigProvider.class)
//    void insertNotificationConfigById(String id, NotificationConfigBaseData input);
//
//    @DeleteProvider(NotificationConfigProvider.class)
//    void deleteNotificationConfigById(String id);

    // Days
    @SelectProvider(NotificationConfigProvider.class)
    List<String> getNotificationConfigDayListById(String id);

//    @InsertProvider(NotificationConfigProvider.class)
//    void insertNotificationConfigDayListById(String id, List<String> days);
//
//    @DeleteProvider(NotificationConfigProvider.class)
//    void deleteNotificationConfigDayListById(String id);

    // Times
    @SelectProvider(NotificationConfigProvider.class)
    List<String> getNotificationConfigTimeListById(String id);

//    @InsertProvider(NotificationConfigProvider.class)
//    void insertNotificationConfigTimeListById(String id, List<String> times);
//
//    @DeleteProvider(NotificationConfigProvider.class)
//    void deleteNotificationConfigTimeListById(String id);

    // Parks
    @SelectProvider(NotificationConfigProvider.class)
    List<String> getNotificationConfigParkListById(String id);

//    @InsertProvider(NotificationConfigProvider.class)
//    void insertNotificationConfigParkListById(String id, List<String> parks);
//
//    @DeleteProvider(NotificationConfigProvider.class)
//    void deleteNotificationConfigParkListById(String id);

    // Date Exclusions
    @SelectProvider(NotificationConfigProvider.class)
    List<DateEntity> getNotificationConfigDateExclusionListById(String id);

//    @InsertProvider(NotificationConfigProvider.class)
//    void insertNotificationConfigDateExclusionListById(String id, List<DateEntity> dates);
//
//    @DeleteProvider(NotificationConfigProvider.class)
//    void deleteNotificationConfigDateExclusionListById(String id);

    class NotificationConfigProvider implements ProviderMethodResolver {

        // Base Info
        public String getNotificationConfigBaseDataById(String id) {
            return new SQL() {{
                SELECT("user_id, enabled");
                FROM("notification_config");
                WHERE("user_id = #{id}");
            }}.toString();
        }

        // Days
        public String getNotificationConfigDayListById(String id) {
            return new SQL() {{
                SELECT("day");
                FROM("notification_day");
                WHERE("user_id = #{id}");
            }}.toString();
        }

        // Times
        public String getNotificationConfigTimeListById(String id) {
            return new SQL() {{
                SELECT("time");
                FROM("notification_time");
                WHERE("user_id = #{id}");
            }}.toString();
        }

        // Parks
        public String getNotificationConfigParkListById(String id) {
            return new SQL() {{
                SELECT("park_id");
                FROM("notification_park");
                WHERE("user_id = #{id}");
            }}.toString();
        }

        // Date Exclusions
        public String getNotificationConfigDateExclusionListById(String id) {
            return new SQL() {{
                SELECT("year, month, date");
                FROM("notification_date_exclusion");
                WHERE("user_id = #{id}");
            }}.toString();
        }
    }
}
