package imodv6

import grails.validation.Validateable
import org.springframework.web.multipart.commons.CommonsMultipartFile

@Validateable
class ContentTopicScheduleCO {
    Long id
    List<Long> topicScheduleIds = []
    List<String> scheduleTopicList = []
    List<String> scheduleDate_day = []
    List<String> scheduleDate_month = []
    List<String> scheduleDate_year = []

    ContentTopicScheduleCO() {}

    public String dateCalc(def dateId) {
        return "${this.scheduleDate_day.get(dateId)}/${this.scheduleDate_month.get(dateId)}/${this.scheduleDate_year.get(dateId)}"
    }
}
