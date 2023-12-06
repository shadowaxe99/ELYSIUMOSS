import { GLTFLoader } from 'three';

class ThreeBackground2 {
  constructor() {
    this.loader = new GLTFLoader();
  }

  loadModel(modelPath) {
    return new Promise((resolve, reject) => {
      this.loader.load(
        modelPath,
        (gltf) => {
          resolve(gltf);
        },
        undefined,
        (error) => {
          console.error('An error happened', error);
          reject(error);
        }
      );
    });
  }
}

export default ThreeBackground2;
