package io.gatling.amqp.action

import io.gatling.amqp.config._
import io.gatling.amqp.data._
import io.gatling.amqp.request.builder._
import io.gatling.core.action.Action
import io.gatling.core.action.builder.ActionBuilder
import io.gatling.core.structure.ScenarioContext
import io.gatling.core.util.NameGen

class AmqpActionBuilder(amqpRequestBuilder: AmqpRequestBuilder)(implicit amqp: AmqpProtocol) extends ActionBuilder with NameGen {
  override def build(ctx: ScenarioContext, next: Action): Action = {
    val steps: Iterable[AmqpRequest] = amqpRequestBuilder.build
    if (steps.size == 1) {
      steps.head match {
        case req: PublishRequest =>
          AmqpPublishAction(req, next)
        case req: ConsumeRequest =>
          AmqpConsumeAction(req, next)
      }
    } else {
      // we need some more preprocessing and than return special actor capable of full-filling list of operations one by one
      ???
    }
  }
}
