import java.lang.RuntimeException

class ChatNotFoundException(text: String) : RuntimeException(text)
class MessageNotFoundException(text: String) : RuntimeException(text)

val chatList = mutableListOf<Chat>()

fun createChat(userId: Int, chat: Chat) {
    getChats(chatId = userId).add(chat)
}

fun deleteChat(userId: Int, chatId: Int) {
    for (chatInList in getChats(chatId)) if (chatId == chatInList.chatId) chatList.remove(chatInList)
    else throw ChatNotFoundException("yep, no chat there...")
}

fun createMessage(userId: Int, message: Message) {
    getMessages(userId = userId, chatId = message.chatId).add(message)
}

fun editMessage(userId: Int, chatId: Int, messageId: Int, newNext: String) {
    for (messageInList in getMessages(userId = userId, chatId = chatId)) if (messageId == messageInList.messageId) messageInList.copy(text = newNext)
    else throw MessageNotFoundException("yep, no message there...")
}

fun deleteMessage(userId: Int, chatId: Int, messageId: Int) {
    val messages = getMessages(userId = userId, chatId = chatId)
    for (messageInList in messages) if (messageId == messageInList.messageId) {
        if (messages.size == 1) deleteChat(userId = userId, chatId = chatId)
        messages.remove(messageInList)
    }
}

fun getAmountOfUnreadChats(userId: Int, chatList: MutableList<Chat>): Int {
    val filteredChatList = chatList.filter { it.isRead }
    return filteredChatList.size
}

fun getChats(chatId: Int): MutableList<Chat> {
    return chatList
}

fun getMessages(userId: Int, chatId: Int): MutableList<Message> {
    for (chatInList in getChats(chatId)) if (chatId == chatInList.chatId) return chatInList.messages
    throw MessageNotFoundException("yep, no message there...")
}
