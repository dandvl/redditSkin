package com.danielm.redditskin.mvvm

import com.danielm.redditskin.api.WebServices

class Repository : IRepository  {

    override suspend fun posts() = WebServices.all.top("all", 10)

}