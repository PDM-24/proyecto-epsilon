import mongoose from 'mongoose';

const imageSchema = new mongoose.Schema({
  public_id: String,
  secure_url: String,
});

const scheduleSchema = new mongoose.Schema({
  day: {
    type: String,
    required: true,
  },
  startHour: {
    type: String,
    required: true,
  },
  startMinute: {
    type: String,
    required: true,
  },
  finishHour: {
    type: String,
    required: true,
  },
  finishMinute: {
    type: String,
    required: true,
  },
});

const publicationSchema = new mongoose.Schema(
  {
    propertyType: {
      type: String,
      required: true,
    },
    neighborhood: {
      type: String,
      required: true,
    },
    municipality: {
      type: String,
      required: true,
    },
    department: {
      type: String,
      required: true,
    },
    propertyAddress: {
      type: String,
      required: true,
    },
    longitude: {
      type: Number,
      required: true,
    },
    latitude: {
      type: Number,
      required: true,
    },
    propertySize: {
      type: String,
      required: true,
    },
    propertyBedrooms: {
      type: String,
      required: true,
    },
    propertyBathrooms: {
      type: String,
      required: true,
    },
    propertyFloors: {
      type: String,
      required: true,
    },
    propertyParking: {
      type: Number, // Cambiado a número
      required: true,
    },
    propertyFurnished: {
      type: String,
      required: true,
    },
    propertyDescription: {
      type: String,
      required: true,
    },
    propertyPrice: {
      type: String,
      required: true,
    },
    scheduleViewing: [scheduleSchema],
    images: [imageSchema], 
    seller: {
      type: mongoose.Schema.Types.ObjectId,
      ref: 'User',
      required: true,
    },
    status: {
      type: String,
      enum: ['unapproved', 'approved', 'pending'],
      default: 'approved',
    },
  },
  {
    timestamps: true,
  }
);

export default mongoose.model("Publication", publicationSchema);