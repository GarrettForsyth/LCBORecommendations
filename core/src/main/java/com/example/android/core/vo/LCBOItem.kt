package com.example.android.core.vo

import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * EXAMPLE API RESPONSE:
 * {"status":200,"message":null,"result":
 *  { "id":438457,
 *    "is_dead":false,
 *    "name":"Longslice Hopsta La Vista",
 *    "tags":"longslice hopsta la vista beer ale canada ontario brewery inc can",
 *    "is_discontinued":false,
 *    "price_in_cents":315,
 *    "regular_price_in_cents":315,
 *    "limited_time_offer_savings_in_cents":0,
 *    "limited_time_offer_ends_on":null,
 *    "bonus_reward_miles":0,
 *    "bonus_reward_miles_ends_on":null,
 *    "stock_type":"LCBO",
 *    "primary_category":"Beer",
 *    "secondary_category":"Ale",
 *    "origin":"Canada, Ontario",
 *    "package":"473 mL can",
 *    "package_unit_type":"can",
 *    "package_unit_volume_in_milliliters":473,
 *    "total_package_units":1,
 *    "volume_in_milliliters":473,
 *    "alcohol_content":650,
 *    "price_per_liter_of_alcohol_in_cents":1024,
 *    "price_per_liter_in_cents":665,
 *    "inventory_count":1946,
 *    "inventory_volume_in_milliliters":920458,
 *    "inventory_price_in_cents":612990,
 *    "sugar_content":null,
 *    "producer_name":"Longslice Brewery Inc",
 *    "released_on":null,
 *    "has_value_added_promotion":false,
 *    "has_limited_time_offer":false,
 *    "has_bonus_reward_miles":false,
 *    "is_seasonal":false,
 *    "is_vqa":false,
 *    "is_ocb":false,
 *    "is_kosher":false,
 *    "value_added_promotion_description":null,
 *    "description":null,
 *    "serving_suggestion":"Serve with a burger topped with caramelized onions, or grilled shrimp and guacamole.",
 *    "tasting_note":"Gold medal winner at the 2015 and 2017 Ontario Brewing Awards. In the glass, a hazy copper colour, with a generous lacy head. A rich malty nose is punctuated by forward hops character, contributing citrus and herbal notes. Balanced on the palate, with toasty and malty flavours leading to a lengthy and refreshing finish.",
 *    "updated_at":"2019-01-21T14:32:16.313Z",
 *    "image_thumb_url":"https://dx5vpyka4lqst.cloudfront.net/products/438457/images/thumb.png",
 *    "image_url":"https://dx5vpyka4lqst.cloudfront.net/products/438457/images/full.jpeg",
 *    "varietal":"American Ipa",
 *    "style":"Medium \u0026 Hoppy",
 *    "tertiary_category":"India Pale Ale (IPA)",
 *    "sugar_in_grams_per_liter":null,
 *    "clearance_sale_savings_in_cents":0,
 *    "has_clearance_sale":false,
 *    "product_no":438457
 *    }
 */

@Entity(tableName = "lcbo_items")
data class LCBOItem(
    @PrimaryKey var id :Int,
    var name: String,
    var tags: String,
    var priceInCents: Int,
    var regularPriceInCents: Int,
    var primaryCategory: String, // TODO Get all categories(!?)
    var secondaryCategory: String,
    var tertiaryCategory: String,
    var origin: String, // TODO Get all origins (!?)
    var packageUnitType: String,
    var packageUnitVolumeInMilliliters: Int,
    var totalPackageUnits: Int,
    var volumeInMilliliters: Int,
    var alcoholContent: Int,
    var pricePerLiterOfAlcoholInCents: Int,
    var pricePerLiterInCents: Int,
    var sugarContent: Int,
    var producerName: String,
    var releasedOn: String,
    var hasValueAddedPromotion: Boolean,
    var hasLimitedTimeOffer: Boolean,
    var isSeasonal: Boolean,
    var description: String,
    var servingSuggestion: String,
    var tastingNote: String,
    var imageThumbUrl: String,
    var imageUrl: String,
    var varietal: String,
    var style: String,
    var isDead: Boolean,
    var isDiscontinued: Boolean
)