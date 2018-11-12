package com.robpridham.chucknorrisapp.config

class LocalConfig {
    //TODO: would be better as remote config with app bootstrap etc
    //TODO: failing that, local resources e.g. XML

    companion object {
        const val CN_SERVER_PRIMARY_URL = "https://api.icndb.com"
        const val CN_RANDOM_JOKES_ENDPOINT = "/jokes/random"
    }
}