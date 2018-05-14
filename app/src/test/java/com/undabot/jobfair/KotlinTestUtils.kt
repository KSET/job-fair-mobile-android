package com.undabot.jobfair

import junit.framework.Assert.assertEquals

fun `Given`(unit: () -> Unit) = unit.invoke()
fun `When`(unit: () -> Unit) = unit.invoke()
fun `Then`(unit: () -> Unit) = unit.invoke()
infix fun Any?.`And`(unit: () -> Unit) = unit.invoke()
infix fun Any?.equals(expected: kotlin.Any?) = assertEquals(expected, this)