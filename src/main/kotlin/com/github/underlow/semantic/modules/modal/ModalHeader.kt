@file:JsModule("semantic-ui-react/dist/commonjs/modules/Modal/ModalHeader")

package com.github.underlow.semantic.modules.modal

import com.github.underlow.semantic.SemanticShorthandContent
import react.RClass
import react.RProps
import react.ReactElement

external interface ModalHeaderProps : RProps {
  var `as`: Any?
  var children: ReactElement?
  var className: String?
  var content: SemanticShorthandContent?
}

@JsName("default")
external var ModalHeader: RClass<ModalHeaderProps> = definedExternally
