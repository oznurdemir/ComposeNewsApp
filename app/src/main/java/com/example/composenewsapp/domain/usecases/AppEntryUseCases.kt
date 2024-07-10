package com.example.composenewsapp.domain.usecases

data class AppEntryUseCases(
    val readAppEntry: ReadAppEntry,
    val saveAppEntry: SaveAppEntry
    //Bu sınıf, uygulama sınıfınızı tanımlar ve Hilt'i kullanarak bağımlılık enjeksiyonunu etkinleştirir.
)
