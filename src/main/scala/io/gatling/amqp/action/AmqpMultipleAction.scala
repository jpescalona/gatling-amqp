package io.gatling.amqp.action

import akka.actor.{ActorRef, Props}
import io.gatling.amqp.config.AmqpProtocol
import io.gatling.amqp.data.AmqpRequest
import io.gatling.amqp.infra.Logging
import io.gatling.core.action.Chainable
import io.gatling.core.session.Session

/**
  * Created by Ľubomír Varga on 19.5.2016.
  */
class AmqpMultipleAction(req: AmqpRequest, val nextList: Iterable[ActorRef])(implicit amqp: AmqpProtocol) extends Chainable with Logging {
  override def next: ActorRef = {
    //AmqpMultipleAction nextList.tail
    // TODO need to build AmqpMultipleAction(req = nextList.head, nextList.tail). If there are only head, just return it
    ???
  }

  override def execute(session: Session): Unit = {
    //amqp.router ! AmqpPublishRequest(req, session)
    // TODO handle request or delegate it (point is to have single actor to have guaranteed that response will not come before waiting for it, so probably implement own actor with publish and also consume ability)
    ???
  }
}

object AmqpMultipleAction {
  def props(req: AmqpRequest, nextList: Iterable[ActorRef], amqp: AmqpProtocol) = Props(classOf[AmqpPublishAction], req, nextList, amqp)
}