/*
 * The contents of this file are subject to the Mozilla Public License Version 1.1
 * (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at http://www.mozilla.org/MPL/
 * 
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 * the specific language governing rights and limitations under the License.
 * 
 * The Original Code is JdbcProxy.
 * 
 * The Initial Developer of the Original Code is Frans van Gool.
 * Portions created by the Initial Developer are Copyright (C) 2006
 * Frans van Gool. All Rights Reserved.
 * 
 * Contributor(s): Frans van Gool.
 * 
 * Alternatively, the contents of this file may be used under the terms of the
 * GNU Lesser General Public License (the �LGPL License�), in which case the
 * provisions of LGPL License are applicable instead of those above. If you wish
 * to allow use of your version of this file only under the terms of the LGPL
 * License and not to allow others to use your version of this file under the MPL,
 * indicate your decision by deleting the provisions above and replace them with
 * the notice and other provisions required by the LGPL License. If you do not
 * delete the provisions above, a recipient may use your version of this file
 * under either the MPL or the LGPL License.
 */
package nl.griffelservices.proxy;

import java.lang.reflect.Method;

/**
 * This is the base class for proxy implementations of interfaces.
 * The actual proxy classes are generated by {@link Generator}.
 * Note that the proxy classes themselves do not really implement the interface methods;
 * the actual implementation is deferred to the {@link Handler}.
 * 
 * @author Frans van Gool
 */
public abstract class Proxy
{
  /** The handler that provides the actual implementation if the interface methods. */
  private Handler handler;
  /** The proxy data that can be used by the handler to distinguish one proxy class from another. */ 
  private Object proxyObject;
  
  /**
   * Wrapper around <code>Class.getMethod(String, Class [])</code> that
   * rethrows any checked Exception as a RuntimeException.
   * 
   * @param cls the class containing the method
   * @param name the name of the method
   * @param parameterTypes the parameter types of the method
   * @return the method
   */
  public static Method getMethod(Class cls, String name, Class[] parameterTypes)
  {
    try
    {
      return cls.getMethod(name, parameterTypes);
    }
    catch (RuntimeException e)
    {
      throw e;
    }
    catch (Exception e)
    {
      throw new RuntimeException(e);
    }
  }
  
  /**
   * Constructs a Proxy object.
   * 
   * @param handler the handler that provides the actual implementation if the interface methods
   * @param proxyObject the proxy data that can be used by the handler to distinguish one proxy class from another
   */
  protected Proxy(Handler handler, Object proxyObject)
  {
    this.handler = handler;
    this.proxyObject = proxyObject;
  }
  
  /**
   * Returns the proxy data that can be used by the handler to distinguish one proxy class from another.
   * 
   * @return the proxy data that can be used by the handler to distinguish one proxy class from another
   */
  public Object getProxyObject()
  {
    return proxyObject;
  }
  
  /**
   * Sets the the proxy data that can be used by the handler to distinguish one proxy class from another.
   * 
   * @param proxyObject the proxy data that can be used by the handler to distinguish one proxy class from another
   */
  public void setProxyObject(Object proxyObject)
  {
    this.proxyObject = proxyObject;
  }
  
  /**
   * Narrows the declared return type of a method to an interface type implemented by the actual return value.
   * Only interface types for which a proxy is defined are considered in this process.
   * Returns the declared return type if no narrowing is possible.
   *  
   * @param returnType the declared return type
   * @param returnValue the actual return value
   * @return the narrowed return type
   */
  public abstract Class narrowReturnType(Class returnType, Object returnValue);
  
  /**
   * Factory method that returns a proxy class of the desired type.
   * Returns <code>null</code> if the proxy data is <code>null</code>.
   * Returns the proxy data if no proxy class is defined for the return type. 
   * 
   * @param returnType the interface for which the proxy is desired
   * @param handler the handler to be passed to the proxy constructor
   * @param proxyObject the proxy data to be passed to the proxy constructor
   * @return the proxy class
   */
  public abstract Object getReturnValueProxy(Class returnType, Handler handler, Object proxyObject);

  /**
   * Narrows the declared return type of a method to another interface implemented by the actual return value.
   * Returns the declared return type if no narrowing is possible.
   *  
   * @param returnType the declared return type
   * @param returnValue the actual return value
   * @param otherType the other interface
   * @return the narrowed return type
   */
  protected Class narrowReturnType(Class returnType, Object returnValue, Class otherType)
  {
    if (otherType.isInstance(returnValue) && returnType.isAssignableFrom(otherType))
    {
      return otherType;
    }
    else
    {
      return returnType;
    }
  }

  /**
   * Wrapper around {@link Handler#invoke(Proxy, Method, Object[])} that
   * rethrows any checked Exception as a RuntimeException.
   * 
   * @param method the method that is called
   * @param parameters the parameters of the method that is called
   * @return the return value of the called method
   */
  protected Object invoke(Method method, Object parameters[])
  {
    try
    {
      return handler.invoke(this, method, parameters);
    }
    catch (RuntimeException e)
    {
      throw e;
    }
    catch (Exception e)
    {
      throw new RuntimeException(e);      
    }
  }
}