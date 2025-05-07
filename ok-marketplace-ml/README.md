# Kotlin ONNX ML Sample

Демонстрация использования [ONNX](https://onnxruntime.ai/docs/get-started/with-java.html) в Kotlin на примере NLP
модели [Roberta NER model](https://huggingface.co/xlm-roberta-large-finetuned-conll03-english). Необходимо скачать файлы модели (`model.onnx`, `model.onnx_data`, `tokenizer.json`) в 
папку [onnx-model](onnx-model).


[Ноутбук с python-моделью](./Ml_demo1.ipynb)

# Схема выходных данных
```
t\g 0   1   2   3   4   5   6   7
 1 0.9 0.1 0.0 0.0 0.0 0.0 0.0 0.0
 2 0.0 0.9 0.0 0.0 0.1 0.0 0.0 0.0
 3 0.0 0.0 0.9 0.1 0.0 0.0 0.0 0.0
 4 
``` 
