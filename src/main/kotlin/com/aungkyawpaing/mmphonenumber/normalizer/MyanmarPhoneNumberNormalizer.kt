package com.aungkyawpaing.mmphonenumber.normalizer

import com.aungkyawpaing.mmphonenumber.rule.EnglishNumberRule
import com.aungkyawpaing.mmphonenumber.rule.SanitizeRule
import com.aungkyawpaing.mmphonenumber.rule.ZeroNineRule


class MyanmarPhoneNumberNormalizer(
  private val ruleList: List<Rule> = listOf(
    SanitizeRule(),
    EnglishNumberRule(),
    ZeroNineRule()
  )
) {

  class Builder() {

    private var ruleList = mutableListOf<Rule>()

    fun addRule(rule: Rule) {
      ruleList.add(rule)
    }

    fun build(): MyanmarPhoneNumberNormalizer {
      return MyanmarPhoneNumberNormalizer(ruleList.toList())
    }

  }

  fun normalize(phoneNumber: String): String {
    var normalizedString = phoneNumber

    ruleList.forEach {
      normalizedString = it.convert(normalizedString)
    }

    return normalizedString
  }


}