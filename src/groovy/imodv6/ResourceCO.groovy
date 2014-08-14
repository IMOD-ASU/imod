package imodv6

import grails.validation.Validateable
import org.springframework.web.multipart.commons.CommonsMultipartFile

@Validateable
class ResourceCO {
    Long id
    Long currentContentIdForResource
    List<Long> resourceIds = []
    List<String> resourceNames = []
    List<String> resourceURLs = []
    List<CommonsMultipartFile> files = []
    List<String> types = []

    ResourceCO() {}
}
