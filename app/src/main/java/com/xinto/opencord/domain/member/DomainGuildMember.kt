package com.xinto.opencord.domain.member

import com.xinto.opencord.domain.user.DomainUser
import com.xinto.opencord.domain.user.toDomain
import com.xinto.opencord.rest.dto.ApiGuildMember
import com.xinto.opencord.rest.service.DiscordCdnServiceImpl

data class DomainGuildMember(
    val user: DomainUser?,
    val nick: String? = null,
    val avatarUrl: String? = null,
)

fun ApiGuildMember.toDomain(): DomainGuildMember {
    val avatarUrl = user?.let { user ->
        avatar
            ?.let { DiscordCdnServiceImpl.getUserAvatarUrl(user.id.toString(), it) }
            ?: DiscordCdnServiceImpl.getDefaultAvatarUrl(user.discriminator.toInt().rem(5))
    }
    return DomainGuildMember(
        user = user?.toDomain(),
        nick = nick,
        avatarUrl = avatarUrl,
    )
}
