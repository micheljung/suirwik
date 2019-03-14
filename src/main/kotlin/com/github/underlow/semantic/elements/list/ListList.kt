@file:JsModule("semantic-ui-react/dist/commonjs/elements/List")

package com.github.underlow.semantic.elements.list

import com.github.underlow.semantic.SemanticShorthandContent
import react.RClass
import react.RProps
import react.ReactElement

external interface ListListProps : RProps {
    @nativeGetter
    operator fun get(key: String): Any?

    @nativeSetter
    operator fun set(key: String, value: Any)

    var `as`: Any?
    var children: ReactElement?
    var className: String?
    var content: SemanticShorthandContent?
}

@JsName("default")
external var ListList: RClass<ListListProps> = definedExternally