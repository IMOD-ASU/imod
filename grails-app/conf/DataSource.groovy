dataSource {
    pooled = true
    driverClassName = "org.postgresql.Driver"
    username = "pedagogy"
    password = "imod"
}
hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = false
    cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory'
}
// environment specific settings
environments {
    development {
        dataSource {
            dbCreate = "update" // one of 'create', 'create-drop', 'update', 'validate', ''
            url = "jdbc:postgresql:imod_pedagogy"
        }
    }
    test {
        dataSource {
            dbCreate = "update"
            url = "jdbc:postgresql:imod_pedagogy"
        }
    }
    production {
        dataSource {
            dbCreate = "update"
            url = "jdbc:postgresql:imod_pedagogy"
            pooled = true
            properties {
               maxActive = -1
               minEvictableIdleTimeMillis=1800000
               timeBetweenEvictionRunsMillis=1800000
               numTestsPerEvictionRun=3
               testOnBorrow=true
               testWhileIdle=true
               testOnReturn=true
               validationQuery="SELECT 1"
            }
        }
    }
}