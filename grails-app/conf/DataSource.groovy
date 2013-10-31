dataSource {
    pooled = true
    dbCreate = "update"
    url = "jdbc:mysql://localhost/ambasadoro_prod"
    driverClassName = "com.mysql.jdbc.Driver"
    dialect = org.hibernate.dialect.MySQL5InnoDBDialect
    username = "ambasadoro"
    password = "password"

    properties {
        validationQuery="select 1"
        testWhileIdle=true
        timeBetweenEvictionRunsMillis=60000
    }
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
            dbCreate = "create-drop" // one of 'create', 'create-drop', 'update', 'validate', ''
        }
    }
    test {
        dataSource {
            dbCreate = "update"
        }
    }
    production {
        dataSource {
            dbCreate = "update"
        }
    }
}
