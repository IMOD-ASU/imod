dataSource {
	pooled = true
	driverClassName = 'org.postgresql.Driver'
	username = 'postgres'
	password = 'postgres'
}
hibernate {
	flush.mode = 'auto'
	cache.use_second_level_cache = true
	cache.use_query_cache = false
	cache.region.factory_class = 'org.hibernate.cache.ehcache.SingletonEhCacheRegionFactory'
}
// environment specific settings
environments {
	development {
		dataSource {
			dbCreate = 'update' // one of 'create', 'create-drop', 'update', 'validate', ''
			url = 'jdbc:postgresql:sample'
		}
	}
	test {
		dataSource {
			dbCreate = 'update'
			url = 'jdbc:postgresql:imod-test'
			username = 'imod'
			password = 'imod4u'
		}
	}

	production {
		dataSource {
			dbCreate = 'update'
			url = 'jdbc:postgresql:imod-demo'
			username = 'imod'
			password = 'imod4u'
			pooled = true
			properties {
				maxActive = -1
				minEvictableIdleTimeMillis = 1800000
				timeBetweenEvictionRunsMillis = 1800000
				numTestsPerEvictionRun = 3
				testOnBorrow = true
				testWhileIdle = true
				testOnReturn = true
				validationQuery = 'SELECT 1'
			}
		}
	}
}
