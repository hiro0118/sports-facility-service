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

    @InsertProvider(NotificationConfigProvider.class)
    void insertNotificationConfigBaseDataById(String id, boolean enabled);

    @DeleteProvider(NotificationConfigProvider.class)
    void deleteNotificationConfigBaseDataById(String id);

    // Days
    @SelectProvider(NotificationConfigProvider.class)
    List<String> getNotificationConfigDayListById(String id);

    @InsertProvider(NotificationConfigProvider.class)
    void insertNotificationConfigDayListById(String id, String day);

    @DeleteProvider(NotificationConfigProvider.class)
    void deleteNotificationConfigDayListById(String id);

    // Times
    @SelectProvider(NotificationConfigProvider.class)
    List<String> getNotificationConfigTimeListById(String id);

    @InsertProvider(NotificationConfigProvider.class)
    void insertNotificationConfigTimeListById(String id, String time);

    @DeleteProvider(NotificationConfigProvider.class)
    void deleteNotificationConfigTimeListById(String id);

    // Parks
    @SelectProvider(NotificationConfigProvider.class)
    List<String> getNotificationConfigParkListById(String id);

    @InsertProvider(NotificationConfigProvider.class)
    void insertNotificationConfigParkListById(String id, String park);

    @DeleteProvider(NotificationConfigProvider.class)
    void deleteNotificationConfigParkListById(String id);

    // Date Exclusions
    @SelectProvider(NotificationConfigProvider.class)
    List<DateEntity> getNotificationConfigDateExclusionListById(String id);

    @InsertProvider(NotificationConfigProvider.class)
    void insertNotificationConfigDateExclusionListById(String id, DateEntity date);

    @DeleteProvider(NotificationConfigProvider.class)
    void deleteNotificationConfigDateExclusionListById(String id);

    class NotificationConfigProvider implements ProviderMethodResolver {

        // Base Info
        public String getNotificationConfigBaseDataById(String id) {
            return new SQL() {{
                SELECT("user_id, enabled");
                FROM("notification_config");
                WHERE("user_id = #{id}");
            }}.toString();
        }

        public String insertNotificationConfigBaseDataById(String id, boolean enabled) {
            return new SQL() {{
                INSERT_INTO("notification_config");
                VALUES("user_id, enabled", "#{id}, #{enabled}");
            }}.toString();
        }

        public String deleteNotificationConfigBaseDataById(String id) {
            return new SQL() {{
                DELETE_FROM("notification_config");
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

        public String insertNotificationConfigDayListById(String id, String day) {
            return new SQL() {{
                INSERT_INTO("notification_day");
                VALUES("user_id, day", "#{id}, #{day}");
            }}.toString();
        }

        public String deleteNotificationConfigDayListById(String id) {
            return new SQL() {{
                DELETE_FROM("notification_day");
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

        public String insertNotificationConfigTimeListById(String id, String time) {
            return new SQL() {{
                INSERT_INTO("notification_time");
                VALUES("user_id, time", "#{id}, #{time}");
            }}.toString();
        }

        public String deleteNotificationConfigTimeListById(String id) {
            return new SQL() {{
                DELETE_FROM("notification_time");
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

        public String insertNotificationConfigParkListById(String id, String park) {
            return new SQL() {{
                INSERT_INTO("notification_park");
                VALUES("user_id, park_id", "#{id}, #{park}");
            }}.toString();
        }

        public String deleteNotificationConfigParkListById(String id) {
            return new SQL() {{
                DELETE_FROM("notification_park");
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

        public String insertNotificationConfigDateExclusionListById(String id, DateEntity date) {
            return new SQL() {{
                INSERT_INTO("notification_date_exclusion");
                VALUES("user_id, year, month, date", "#{id}, #{date.year}, #{date.month}, #{date.date}");
            }}.toString();
        }

        public String deleteNotificationConfigDateExclusionListById(String id) {
            return new SQL() {{
                DELETE_FROM("notification_date_exclusion");
                WHERE("user_id = #{id}");
            }}.toString();
        }
    }
}
