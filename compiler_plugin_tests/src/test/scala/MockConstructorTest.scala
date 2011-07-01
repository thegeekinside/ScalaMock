// Copyright (c) 2011 Paul Butcher
// 
// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files (the "Software"), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:
// 
// The above copyright notice and this permission notice shall be included in
// all copies or substantial portions of the Software.
// 
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
// THE SOFTWARE.

package com.borachio.plugin.test

import org.scalatest.Suite
import com.borachio.generated.GeneratedMockFactory
import com.borachio.scalatest.MockFactory
import com.borachio.{CallLogging, VerboseErrors}
import java.net.URL

class MockConstructorTest extends Suite with MockFactory with GeneratedMockFactory with VerboseErrors with CallLogging {
  
  def mockClassPath = new URL("file:compiler_plugin_tests/target/scala_2.9.0/mock-classes/")
  
  def testExpectNewInstanceSimple {
    val m = mock[SimpleClass]
    
    m.expects.newInstance
  
    new SimpleClass
  }
  
  def testExpectNewInstanceWithArgs {
    val m = mock[ClassWithNonTrivialConstructor]
    
    m.expects.newInstance(42, 1.23)
    m.expects.methodWithZeroArguments() returning "some different return value"
    
    expect("some different return value") { UsesClassWithNonTrivialConstructor.doSomething() }
  }
}
