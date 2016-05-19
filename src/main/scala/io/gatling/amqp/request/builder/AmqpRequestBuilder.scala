package io.gatling.amqp.request.builder

import io.gatling.amqp.data._
import io.gatling.core.session.Expression

import scala.collection.mutable

// TODO: use (implicit configuration: GatlingConfiguration)
case class AmqpRequestBuilder(
                               requestName: Expression[String],
                               val _requests: mutable.Builder[AmqpRequest, List[AmqpRequest]] = List.newBuilder
) extends Publishing with Consuming {

  def build: Iterable[AmqpRequest] = _requests.result()
}
