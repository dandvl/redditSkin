package com.danielm.redditskin.data

data class AllAwarding(
    val award_sub_type: String,
    val award_type: String,
    val coin_price: Int,
    val coin_reward: Int,
    val count: Int,
    val days_of_drip_extension: Int,
    val days_of_premium: Int,
    val description: String,
    val end_date: Any,
    val giver_coin_reward: Int,
    val icon_format: String,
    val icon_height: Int,
    val icon_url: String,
    val icon_width: Int,
    val id: String,
    val is_enabled: Boolean,
    val is_new: Boolean,
    val name: String,
    val penny_donate: Int,
    val penny_price: Int,
    val resized_icons: List<ResizedIcon>,
    val start_date: Any,
    val subreddit_coin_reward: Int,
    val subreddit_id: Any
)