# Kotlin JS wrapper for Semantic UI

Kotlin wrapper library to use [Semantic UI React](https://react.semantic-ui.com) in Kotlin JS project.

Inspired by [Muirwik](https://github.com/cfnz/muirwik) and forked from [semantic-ui-kotlin-wrapper](https://github.com/underlow/semantic-ui-kotlin-wrapper)

This is Proof of Concept. Be careful using it. Feel free to submit tickets or pull requests. 

***Usage*** 

- Build 
  -  by `gradle build` 
  -  Add jar file to your project
- OR use maven
  - add [https://jitpack.io](https://jitpack.io) to list of maven repositories (see docs on [https://jitpack.io](https://jitpack.io)
  - add `compile "com.github.underlow:semantic-ui-kotlin-wrapper:master-SNAPSHOT"` to `build.gradle`

- Add semantic ui react npm package, usually that means add **"semantic-ui-react": "^0.82.1"** to **package.json**
- Try somthing like 
```kotlin

class LoginComponent : RComponent<RProps, RState>() {
    override fun RBuilder.render() {
        Button {
            attrs {
                fluid = true
                color = "teal"
                size = "large"
            }
            +"Login"
        }
    }
}

```
