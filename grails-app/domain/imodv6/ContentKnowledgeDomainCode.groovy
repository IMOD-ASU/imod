package imodv6

class ContentKnowledgeDomainCode {
    String description

    static constraints = {
    }

    static mapping = {
        version false
    }

    def String toString() {
        return description
    }

    public static List<ContentKnowledgeDomainCode> values() {
        return ContentKnowledgeDomainCode.list() ?: []
    }
}
