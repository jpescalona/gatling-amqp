package io.gatling.amqp.action

import akka.actor.{ActorRef, ActorSystem}
import io.gatling.amqp.config._
import io.gatling.amqp.data._
import io.gatling.amqp.request.builder._
import io.gatling.core.action.Action
import io.gatling.core.action.builder.ActionBuilder
import io.gatling.core.util.NameGen
import io.gatling.core.structure.ScenarioContext

class AmqpActionBuilder(amqpRequestBuilder: AmqpRequestBuilder)(implicit amqp: AmqpProtocol) extends ActionBuilder with NameGen {
  def build(system: ActorSystem, next: Action, ctx: ScenarioContext): ActorRef = {
    val steps: Iterable[AmqpRequest] = amqpRequestBuilder.build
    if (steps.size == 1) {
      steps.head match {
        case req: PublishRequest =>
          system.actorOf(AmqpPublishAction.props(req, next, amqp), genName("AmqpPublishAction"))
        case req: ConsumeRequest =>
          system.actorOf(AmqpConsumeAction.props(req, next, amqp), genName("AmqpConsumeAction"))
      }
    } else {
      // we need some more preprocessing and than return special actor capable of full-filling list of operations one by one
      ???
    }
  }

  override def build(ctx: ScenarioContext, next: Action): Action = ???
}
