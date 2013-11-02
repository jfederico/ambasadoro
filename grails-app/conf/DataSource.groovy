dataSource {
    pooled = false
	dbCreate = "update" // one of 'create', 'create-drop', 'update', 'validate', ''
    driverClassName = "com.mysql.jdbc.Driver"
    //dialect = org.hibernate.dialect.MySQLInnoDBDialect
    dialect = org.hibernate.dialect.MySQL5InnoDBDialect	//For MySQL 5.5 and newer
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
            pooled = true
            url = "jdbc:mysql://localhost/ambasadoro_dev"
        }
    }
    test {
        dataSource {
            dbCreate = "create-drop"
            url = "jdbc:mysql://localhost/ambasadoro_test"
        }
    }
    production {
        dataSource {
            pooled = true
            url = "jdbc:mysql://localhost/ambasadoro_prod"
        }
    }
}
