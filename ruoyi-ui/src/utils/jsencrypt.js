import JSEncrypt from 'jsencrypt/bin/jsencrypt.min'

// 密钥对生成 http://web.chacuo.net/netrsakeypair

const publicKey = 'MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDP8ivBWimFLTDxh20xAym3Xrp7' +
  'OryKbncdaiRCiWrbvvZKvWqMU4pdsCMo9o1U0EuMHQisdZTBuSbJCgvl/wjAxrp5' +
  'Y7FPX5svcqwnvUpbeiHldnhmj+0JN3bztdnkEoLfVvCR9M+BovsF4tX64R7VKgHl' +
  '6Bzmo4G1m55Qu/iDXQIDAQAB'

const privateKey = 'MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAM/yK8FaKYUtMPGH' +
  'bTEDKbdeuns6vIpudx1qJEKJatu+9kq9aoxTil2wIyj2jVTQS4wdCKx1lMG5JskK' +
  'C+X/CMDGunljsU9fmy9yrCe9Slt6IeV2eGaP7Qk3dvO12eQSgt9W8JH0z4Gi+wXi' +
  '1frhHtUqAeXoHOajgbWbnlC7+INdAgMBAAECgYAc/PV8jsUa+6HHCcn++yHlnh8A' +
  '7r85zQxt3U4QuHddCtHquV0REtRHGzbLjxNEr4TYk3OSFxIVr2novK3L8jGV7bEr' +
  '7anJZJ1OGqvqaRXEOl9Hf3a+K/0XnEnvcEzDiCr8bMA5oZQm1DAtuiFLcSKQN8xt' +
  'e5cWAeG3PkpljJHcAQJBAPPLQdauejd3Pd0G3SiltiMtFrgdtnLG3WeS9/W2a0VG' +
  'hrBG2puP/8ReYr9OLC25cUM4We8B+qWZUO4Vm7q3ai0CQQDaW3Hd//HlJo66IOPF' +
  'gcA8Z/pEdMH4hKtTNbNIF0JqMefm3nDoxx4a8N7tTykyVMOOHFdLBc4mfW79Srqj' +
  'WCvxAkEA2BpLMS6WPYZgewUzk21Yx1OXePs1mAY5ii8Xx2G9MroyeB1GeHCrqnh0' +
  'FdoIhTuFb62K4rchZBtERtc2pMsivQJAc5PNjKVJ43HqQCjihwtrtk2PEab/eMy4' +
  'Zn3ZHIJ7O/yYdAIvn3atv7LZPKEW/DYToGft6J71BDiGg/mHGqiz8QJBAIuTfrHj' +
  'b3BKoT+2dcGzM2eMRFQ+N8FQpDllf/LY7n4/3VK/qcFZ70tUInr8GAlDPxxo3jBB' +
  'BNTuuSSGLAbdZqQ='

// 加密
export function encrypt(txt) {
  const encryptor = new JSEncrypt()
  encryptor.setPublicKey(publicKey) // 设置公钥
  return encryptor.encrypt(txt) // 对数据进行加密
}

// 解密
export function decrypt(txt) {
  const encryptor = new JSEncrypt()
  encryptor.setPrivateKey(privateKey) // 设置私钥
  return encryptor.decrypt(txt) // 对数据进行解密
}

