package fyi.pauli.schuelerzeitung.util.serializer

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.*
import java.util.*

object UUIDLongSerializer : KSerializer<UUID> {
	override val descriptor: SerialDescriptor = buildClassSerialDescriptor("UUID") {
		element<Long>("msb")
		element<Long>("lsb")
	}

	override fun serialize(encoder: Encoder, value: UUID) {
		encoder.encodeStructure(descriptor) {
			encodeLongElement(descriptor, 0, value.mostSignificantBits)
			encodeLongElement(descriptor, 1, value.leastSignificantBits)
		}
	}

	@OptIn(ExperimentalSerializationApi::class)
	override fun deserialize(decoder: Decoder): UUID {
		return decoder.decodeStructure(descriptor) {
			var mostSignificationBits: Long? = null
			var leastSignificationBits: Long? = null

			if (decodeSequentially()) {
				mostSignificationBits = decodeLongElement(descriptor, 0)
				leastSignificationBits = decodeLongElement(descriptor, 1)
			} else {
				while (true) {
					when (val index = decodeElementIndex(descriptor)) {
						0 -> mostSignificationBits = decodeLongElement(descriptor, 0)
						1 -> leastSignificationBits = decodeLongElement(descriptor, 1)
						CompositeDecoder.DECODE_DONE -> break
						else -> error("Unexpected index: $index")
					}
				}
			}

			requireNotNull(mostSignificationBits)
			requireNotNull(leastSignificationBits)

			UUID(mostSignificationBits, leastSignificationBits)
		}
	}
}