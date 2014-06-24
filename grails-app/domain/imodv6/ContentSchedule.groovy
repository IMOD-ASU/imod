package imodv6

class ContentSchedule {
    Content content
    Date startDate
    Imod imod

    static constraints = {
    }

    static mapping = {
        version false
    }

    def String toString() {
        return "${content.topicTitle}:${startDate}"
    }
}
