package com.gahov.musenergy.arch.router.command

sealed class Command {
    object Back : Command()
    object Root : Command()
    object Close : Command()

    abstract class Route : Command()
    abstract class FeatureCommand : Command()
}