@file:JsModule("semantic-ui-react/dist/commonjs/elements/List/ListDescription")

package com.github.underlow.semantic.elements.list

import com.github.underlow.semantic.SemanticShorthandContent
import react.RClass
import react.RProps
import react.ReactElement

external interface ListDescriptionProps : RProps {
  var `as`: Any?
  var children: ReactElement?
  var className: String?
  var content: SemanticShorthandContent?
}

@JsName("default")
external var ListDescription: RClass<ListDescriptionProps> = definedExternally
