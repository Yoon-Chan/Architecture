package com.example.booksearchapp

import com.example.booksearchapp.data.db.*
import com.example.booksearchapp.ui.view.*
import kotlinx.coroutines.*
import org.junit.runner.RunWith
import org.junit.runners.Suite


@RunWith(Suite::class)
@Suite.SuiteClasses(
    MainActivityTest::class,
    BookSearchDaoTest::class
)
class InstrumentedTestSuite