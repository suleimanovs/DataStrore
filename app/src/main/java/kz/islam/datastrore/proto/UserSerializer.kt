package kz.islam.datastrore.proto

data User( val name: String, val age: String)

object UserSerializer : Serializer<User> {

    override fun readFrom(input: InputStream): User {
        try {
            return User.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override fun writeTo(t: User, output: OutputStream) = t.writeTo(output)
}