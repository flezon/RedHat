listView('test') {
    description('All unstable jobs for project A')
    filterBuildQueue()
    filterExecutors()
    jobs {
        regex(/jenkins-.+/)
    }
    jobFilters {
        status {
            status(Status.UNSTABLE)
        }
    }
    columns {
        status()
        weather()
        name()
        lastSuccess()
        lastFailure()
        lastDuration()
        buildButton()
    }
}