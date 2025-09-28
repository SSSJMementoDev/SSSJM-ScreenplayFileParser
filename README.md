# SSSJM-ScreenplayFileParser

![Build](https://github.com/SSSJMementoDev/SSSJM-ScreenplayFileParser/workflows/Build/badge.svg)
[![Version](https://img.shields.io/jetbrains/plugin/v/MARKETPLACE_ID.svg)](https://plugins.jetbrains.com/plugin/MARKETPLACE_ID)
[![Downloads](https://img.shields.io/jetbrains/plugin/d/MARKETPLACE_ID.svg)](https://plugins.jetbrains.com/plugin/MARKETPLACE_ID)

<!-- Plugin description -->
## 支持IntelliJ IDE版本 : 2025.2.1+
本项目用于辅助编写歌庭剧本脚本（转用于SSSJM的剧本脚本系统）
<!-- Plugin description end -->

### 文件格式
+ ".gss"
+ ".gss.txt"

### 命令名（主要参数值类型）
+ SetTextBox
    + Enum（文本框类型）
+ BGM
    + String（音频文件注册名或文件可寻址名）
+ PlaySound
    + String（音频文件注册名或文件可寻址名）
+ PlayVideo
    + String（视频文件注册名或文件可寻址名）
+ ChangeBG
    + String（文件可寻址名）
+ ChangeFigure
    + String（预制体文件可寻址名）
+ CallScene
    + String（剧本文件可寻址名）
+ Choose
    + String（选择项标题）
+ Label
    + String（标签命名）
+ JumpLabel
    + String（跳转目标标签名）
+ GetUserInput
    + String（赋值目标变量名）
+ SetVar
    + String（赋值目标变量名）
+ SetAnimation
    + String（动画注册名）
+ SetTransform
    + Json（自定义结构类TransformDelta序列化得Json）
+ GenObject
    + String（生成后游戏对象名）

### 次级参数键（次级参数值类型）
+ next
    + null（命令执行完毕则立刻执行下一步）
+ async
    + null（当前命令挂到异步执行，立刻执行下一步）
+ concat
    + null（下一次文本刷新不覆盖原有文本，而是追加内容）
+ hinder
    + null（自动播放模式下阻止自动执行）
+ left
    + null（当前命令操作对象指向左演员位）
+ right
    + null（当前命令操作对象指向右演员位）
+ center
    + null（当前命令操作对象指向中演员位）
+ clear
    + null（执行当前命令时清空文本，非台词播放命令将藏起台词文本框）
+ global
    + null（将当前变量变为游戏全局变量）
+ id
    + String（生成后或目标游戏对象名）
+ target
    + null（目标游戏对象名）
+ transform
    + Json（自定义结构类TransformDelta序列化得Json）
+ option
    + optionTarget
        + String（剧本名或标签名）
    + optionContent
        + String（选项按钮文本）
+ volume
    + float（音量值）
+ voice
    + String（音频文件注册名或文件可寻址名）
+ skipOff
    + null（无）
+ enter
    + String（渐变方式注册名/音视频不共用！）
+ exit
    + String（渐变方式注册名/音视频不共用！）
+ motion
    + String（动画注册名）
+ layer
    + int（图层数值）
+ title
    + String（输入框标题文本）
+ buttonText
    + String（按钮文本）
+ defaultText
    + String（输入框底层默认文本）
+ duration
    + int（命令执行时间跨度）
+ when
    + String（简单条件判断算术表达式）
+ state
    + Enum（指定命令执行状态）

